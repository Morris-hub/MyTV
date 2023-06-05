package com.example.mytv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.*

class Nearby : ComponentActivity() {

    private lateinit var connectionsClient: ConnectionsClient
    private var isDeviceConnected = false
    private var isNearbyEnabled by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionsClient = Nearby.getConnectionsClient(this)

        setContent {
            NearbyApp()
        }
    }

    @Composable
    private fun NearbyApp() {
        var switchState by remember { mutableStateOf(false) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isNearbyEnabled) {
                Text(text = "Nearby ist eingeschaltet")
                if (isDeviceConnected) { Text(text = "Gerät gefunden") }
            } else {
                Text(text = "Nearby ist ausgeschaltet")
            }
            Switch(
                checked = switchState,
                onCheckedChange = { checked ->
                    switchState = checked
                    handleSwitchChange(checked)
                }
            )
        }
    }

    private fun handleSwitchChange(checked: Boolean) {
        if (checked) {
            startAdvertising()
            startDiscovery()
            isNearbyEnabled = true
        } else {
            stopAdvertising()
            stopDiscovery()
            isNearbyEnabled = false
        }
    }

    private fun startAdvertising() {
        val advertisingOptions = AdvertisingOptions.Builder()
            .setStrategy(Strategy.P2P_POINT_TO_POINT)
            .build()

        connectionsClient.startAdvertising(
            "Nearby",
            packageName,
            connectionLifecycleCallback,
            advertisingOptions
        )
    }

    private fun startDiscovery() {
        val discoveryOptions = DiscoveryOptions.Builder()
            .setStrategy(Strategy.P2P_POINT_TO_POINT)
            .build()

        connectionsClient.startDiscovery(
            "Nearby",
            endpointDiscoveryCallback,
            discoveryOptions
        )
    }

    private fun stopAdvertising() {
        connectionsClient.stopAdvertising()
    }

    private fun stopDiscovery() {
        connectionsClient.stopDiscovery()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAdvertising()
        stopDiscovery()
    }

    private val connectionLifecycleCallback = object : ConnectionLifecycleCallback() {
        override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {
            // Automatically accept the connection on both sides.
            connectionsClient.acceptConnection(endpointId, payloadCallback)
        }

        override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {
            when (result.status.statusCode) {
                ConnectionsStatusCodes.STATUS_OK -> {
                    print("success")
                    // Connection successful.
                    isDeviceConnected = true

                }
                ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED -> {
                    // Connection rejected by other side.
                    print("rejected")

                    isDeviceConnected = false

                }
                ConnectionsStatusCodes.STATUS_ERROR -> {
                    // Error occurred while attempting to connect.
                    print("failed")

                    isDeviceConnected = false

                }
            }
        }

        override fun onDisconnected(endpointId: String) {
            // Connection disconnected.
        }
    }

    private val endpointDiscoveryCallback = object : EndpointDiscoveryCallback() {
        override fun onEndpointFound(endpointId: String, info: DiscoveredEndpointInfo) {
            // Automatically initiate the connection on both sides.
            connectionsClient.requestConnection("Nearby", endpointId, connectionLifecycleCallback)
        }

        override fun onEndpointLost(endpointId: String) {
            // Endpoint lost.
        }
    }

    private val payloadCallback = object : PayloadCallback() {
        override fun onPayloadReceived(endpointId: String, payload: Payload) {
            // Payload received.
        }

        override fun onPayloadTransferUpdate(endpointId: String, update: PayloadTransferUpdate) {
            // Payload transfer status updated.
        }
    }
}
