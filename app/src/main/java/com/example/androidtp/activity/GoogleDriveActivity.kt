package com.example.androidtp.activity

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ammarptn.gdriverest.DriveServiceHelper
import com.ammarptn.gdriverest.DriveServiceHelper.getGoogleDriveService
import com.example.androidtp.R
import com.example.androidtp.utils.DriveManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.services.drive.DriveScopes
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_google_drive.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


// Gdrive 1069212540071-hi1tk6o94gbboe6pdldhk7384hig38g8.apps.googleusercontent.com
class GoogleDriveActivity : AppCompatActivity() {


    var progressDialog: ProgressDialog? = null
    private var driveManager: DriveManager? = null
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
    private var mDriveServiceHelper: DriveServiceHelper? = null


    companion object {
        const val REQUEST_CODE_SIGN_IN = 0

        const val LOCAL_FILE_NAME = "fileUpload.txt"
        const val REMOTE_FILE_NAME = "test-text"

        val TAG = GoogleDriveActivity::class.java.simpleName

    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(applicationContext)

        if (account == null) {

            signIn()

        } else {


            name.text = account.email

            mDriveServiceHelper = DriveServiceHelper(getGoogleDriveService(applicationContext, account, "appName"))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_drive)


        progressDialog = ProgressDialog(this);
        progressDialog?.setIndeterminate(true);
        progressDialog?.setCancelable(true);
        progressDialog?.setMessage("Fetching from server..");


        initView()


    }

    private fun initView() {
        upload.setOnClickListener { upload() }
        restore.setOnClickListener { restore() }
        find.setOnClickListener { find() }
        delete_local.setOnClickListener { deleteLocal() }
        delete.setOnClickListener { delete() }
        find_local.setOnClickListener { findLocal() }
        create_local.setOnClickListener { createLocal() }


        signIn()
        initFile()

    }


    // user sign in
    private fun signIn() {
        val mGoogleSignInCLiend = buildGoogleSignInClient()
        startActivityForResult(mGoogleSignInCLiend.signInIntent, REQUEST_CODE_SIGN_IN)


    }

    private fun buildGoogleSignInClient(): GoogleSignInClient {
        return GoogleSignIn.getClient(
            this@GoogleDriveActivity,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(DriveScopes.DRIVE_FILE), Scope(DriveScopes.DRIVE_APPDATA))
                .requestEmail()
                .build()
        )
    }

    private fun initFile() {
        val file = File(this.filesDir, LOCAL_FILE_NAME)
        file.writeText("This is the test file text")


    }

    private fun upload() {
        GlobalScope.launch {
            driveManager?.upload(
                File(filesDir, LOCAL_FILE_NAME),
                "text/plain",
                REMOTE_FILE_NAME
            )
        }
        Log.d("@@@", "upload file")

    }

    // restore file
    private fun restore() {
        GlobalScope.launch(Dispatchers.IO) {
            val files = driveManager?.query(REMOTE_FILE_NAME)
            files?.forEach { file ->
                val localFile = File(filesDir, LOCAL_FILE_NAME)
                Log.d(
                    "@@@",
                    "before restore local file last modified time: ${dateFormat.format(localFile.lastModified())}"
                )
              //  driveManager?.download(file.id, localFile.absolutePath)
                Log.d(
                    "@@@", "restore file: ${file.name}, " +
                            "local file modified time : ${dateFormat.format(localFile.lastModified())}"
                )
            }
        }
    }

    // find file
    private fun find() {
        GlobalScope.launch(Dispatchers.IO) {
            /*val files = driveManager?.query(REMOTE_FILE_NAME)
            Log.d("@@@", "find files size: ${files?.size}")
            files?.forEach {
                Log.d("@@@", "find file: ${it.name}")
            }
            driveManager?.isAppFolderExists()*/


            Dexter.withActivity(this@GoogleDriveActivity)
                .withPermissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {

                    }

                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                        if (report?.areAllPermissionsGranted()!!) {


//                            Uploaded NEW File
                            /*

                            val file = File(this@GoogleDriveActivity.filesDir, LOCAL_FILE_NAME)
                             file.writeText("This is the test file text")


                             mDriveServiceHelper?.uploadFile(file, "text/plain", null)?.addOnSuccessListener {

                                 val gson = Gson()
                                 Log.d(TAG, "onSuccess: " + gson.toJson(it))

                             }?.addOnFailureListener { e -> Log.d(TAG, "onFailure: " + e.message) }

                             */

                            //                            Download NEW File
                            /* mDriveServiceHelper?.downloadFile(
                                 File(
                                     Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                     , "dummy file.txt"
                                 ),
                                 "https://drive.google.com/drive/u/0/folders/1p_dgbpbRdNHbcNU-a0GQ0FfVxkLS4RN7"
                             )?.addOnSuccessListener {
                                 Toast.makeText(this@GoogleDriveActivity,"File Downloaded $it",Toast.LENGTH_LONG).show()

                             }?.addOnFailureListener { e -> Log.d(TAG, "onFailure: " + e.message) }*/


                            mDriveServiceHelper?.downloadFile(
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                ,
                                "12tosZYcuc3CCqBgdiQx1vHDr_0Vn5oRz"
                            )


//                            Query Uploaded Files

                            /*

                                 val fileList: Task<FileList>? = mDriveServiceHelper?.queryFiles()
                                 fileList?.addOnSuccessListener {
                                     it.files.forEach {
                                         Log.i(TAG, "Files: ${it.capabilities}")
                                     }
                                 }?.addOnFailureListener { e -> Log.d(TAG, "onFailure: " + e.message) }

                                 Log.i(TAG, fileList.toString())
                              */
                        }

                    }

                }).check()


        }
    }

    // delete file
    private fun delete() {
        GlobalScope.launch(Dispatchers.IO) {
            val files = driveManager?.query(REMOTE_FILE_NAME)
            files?.forEach { file ->
                driveManager?.delete(file)
                Log.d("@@@", "delete file: ${file.name}")
            }
        }
    }

    // create file in locally
    private fun createLocal() {
        val file = File(filesDir, LOCAL_FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("file created by use click")
            Log.d("@@@", "create file")
        } else {
            Log.d("@@@", "local file exists")
        }
    }

    // find local file if exist
    private fun findLocal() {
        val file = File(filesDir, LOCAL_FILE_NAME)
        Log.d("@@@", "find local file: ${file.exists()}")
    }

    // delete local file
    private fun deleteLocal() {
        File(filesDir, LOCAL_FILE_NAME).delete()
        Log.d("@@@", "delete local file")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN && resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener {

                    mDriveServiceHelper = DriveServiceHelper(
                        getGoogleDriveService(
                            getApplicationContext(),
                            it,
                            getString(R.string.app_name)
                        )
                    );

                    Log.d(TAG, "handleSignInResult: " + mDriveServiceHelper);


                    Log.w(
                        "Success:",
                        "signInResult code= Name: ${it!!.displayName}  Server Token: ${it.photoUrl}"
                    )
                    driveManager = DriveManager.getInstance(this)


                }.addOnFailureListener {

                    Log.e(TAG, "Unable to sign in.${it.message}")

                }
        }


    }
}