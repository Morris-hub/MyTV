import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.connection.AdvertisingOptions
import com.google.android.gms.nearby.connection.ConnectionInfo
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback
import com.google.android.gms.nearby.connection.ConnectionResolution
import com.google.android.gms.nearby.connection.ConnectionsClient
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo
import com.google.android.gms.nearby.connection.DiscoveryOptions
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback
import com.google.android.gms.nearby.connection.Payload
import com.google.android.gms.nearby.connection.PayloadCallback
import com.google.android.gms.nearby.connection.PayloadTransferUpdate
import com.google.android.gms.nearby.connection.Strategy

class ViewModel(override val viewModelStore: ViewModelStore) : ViewModel(), ViewModelStoreOwner {
    private lateinit var connectionsClient: ConnectionsClient
    private var isDeviceConnected = false
    private var isNearbyEnabled = false

    init {
        connectionsClient = Nearby.getConnectionsClient(//muss ergänzt werden )
    }

    fun isNearbyEnabled(): Boolean {
        return isNearbyEnabled
    }

    fun isDeviceConnected(): Boolean {
        return isDeviceConnected
    }

    fun startNearby() {
        startAdvertising()
        startDiscovery()
        isNearbyEnabled = true
    }

    fun stopNearby() {
        stopAdvertising()
        stopDiscovery()
        isNearbyEnabled = false
    }

    private fun startAdvertising() {
        val advertisingOptions = AdvertisingOptions.Builder()
            .setStrategy(Strategy.P2P_POINT_TO_POINT)
            .build()

        connectionsClient.startAdvertising(
            "Nearby",
            "com.example.mytv",
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

    private val connectionLifecycleCallback = object : ConnectionLifecycleCallback() {
        override fun onConnectionInitiated(endpointId: String, connectionInfo: ConnectionInfo) {
            // Automatically accept the connection on both sides.
            connectionsClient.acceptConnection(endpointId, payloadCallback)
        }

        override fun onConnectionResult(endpointId: String, result: ConnectionResolution) {
            when (result.status.statusCode) {
                ConnectionsStatusCodes.STATUS_OK -> {
                    // Connection successful.
                    isDeviceConnected = true
                }

                ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED -> {
                    // Connection rejected by other side.
                    isDeviceConnected = false
                }

                ConnectionsStatusCodes.STATUS_ERROR -> {
                    // Error occurred while attempting to connect.
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