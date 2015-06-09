package com.example.bryanty.materialdesignproject.tabs;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bryanty.materialdesignproject.MyApplication;
import com.example.bryanty.materialdesignproject.R;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText editText;
    private Button button;
    private Button button2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment2 newInstance(String param1, String param2) {
        TabFragment2 fragment = new TabFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab_fragment2, container, false);

        editText= (EditText)view.findViewById(R.id.editText_save);
        button= (Button)view.findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createXMLFile(editText.getText().toString());
            }
        });

        button2= (Button)view.findViewById(R.id.button_upload);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadServer();
            }
        });
        return view;
    }

    public void createXMLFile(String text){
        Log.d("asd","you are in "+text);
        MyApplication myApplication=new MyApplication();

        String xmlFile = "userData.xml";
        String userNAme = "username";
        String password = "password";
        try {
            //FileOutputStream fos = new  FileOutputStream("userData.xml");
            FileOutputStream fileos= getActivity().openFileOutput(xmlFile, Context.MODE_PRIVATE);
           // FileOutputStream fileos= getApplicationContext().openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");
            xmlSerializer.startTag(null, "userName");
            xmlSerializer.text(text);
            xmlSerializer.endTag(null, "userName");
            xmlSerializer.startTag(null, "password");
            xmlSerializer.text(text);
            xmlSerializer.endTag(null, "password");
            xmlSerializer.endTag(null, "userData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
            Log.d("asd", "2222 you are in " + dataWrite.getBytes());
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void uploadServer(){
//        ProgressDialog dialog = null;
//        dialog = ProgressDialog.show(uploadServer.this, "", "Uploading file...", true);

//        //xml file directory
//        final String uploadFilePath= getActivity().getFilesDir().toString();
//        final String uploadFileName= "/userData.xml";
//
//        String sourceFileUri= uploadFilePath + "" + uploadFileName ;
//
//        String upLoadServerUri = "http://terminaltester.net16.net/testing/UploadToServer.php";

//        //display directory
//        Log.v("Folder Directory", "folder path> " + getActivity().getFilesDir());
//        Log.v("XML Directory", "file path> " + sourceFileUri);

        //testing
        /**********  File Path *************/
        final String uploadFilePath = getActivity().getFilesDir().toString();
        final String uploadFileName = "/userData.xml";

        /************* Php script path ****************/
        String upLoadServerUri = null;
        upLoadServerUri = "http://terminaltester.net16.net/testing/UploadToServer.php";
        uploadFile(uploadFilePath + "" + uploadFileName, upLoadServerUri);


    }

    public int uploadFile(String sourceFileUri, String upLoadServerUri) {
        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
        int serverResponseCode = 0;

        if (!sourceFile.isFile()) {

            Log.e("uploadFile", "Source File not exist :" + fileName);
            return 0;
        }else{
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

//                dos = new DataOutputStream(conn.getOutputStream());
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""+ fileName + "\"" + lineEnd);
//                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                bytesRead = fileInputStream.read();

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    Toast.makeText(getActivity(), "File Upload Complete.",Toast.LENGTH_SHORT).show();
                }
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                ex.printStackTrace();
                Toast.makeText(getActivity(), "MalformedURLException", Toast.LENGTH_SHORT).show();
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getActivity(), "Got Exception : see logcat ", Toast.LENGTH_SHORT).show();
                Log.e("Upload file Exception", "Exception : " + e.getMessage(), e);
            }
        }

        return serverResponseCode;
    }

}
