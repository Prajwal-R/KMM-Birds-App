import androidx.compose.ui.window.ComposeUIViewController
import dev.prajwal.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
