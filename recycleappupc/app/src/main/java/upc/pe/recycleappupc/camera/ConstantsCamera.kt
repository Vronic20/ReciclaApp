package upc.pe.recycleappupc.camera

import android.Manifest

object ConstantsCamera {

    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CAMERA = 100
    val REQUIRED_PERMISSION_CAMERA = arrayOf(Manifest.permission.CAMERA)
}