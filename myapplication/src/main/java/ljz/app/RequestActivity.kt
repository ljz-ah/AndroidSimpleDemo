package ljz.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_request.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

/**
 *项目名称：LJZ_DEMO
 *@author LJZ
 *Created Time  2020/4/22 11:24.
 *@version 1.0
 */
public class RequestActivity : BaseActivity() {
    lateinit var iv_showDonw: ImageView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout());

        initData()
    }

    override fun getLayout(): Int {
        return R.layout.activity_request;
    }


    override fun initData() {
        progressBar.visibility = View.GONE
    }

    //
    fun loadImageForHttpClient(view: View) {
        loadImage(0)

    }

    //
    fun loadImageForHttpUrlConnection(view: View) {
        loadImage(1)

    }

    fun loadImageForOkHttp(view: View) {
        loadImage(2)

    }


    /**
     *通过不同的请求方式加载图片  0，httpclient,1,HttpUrlConnection
     */
    fun loadImage(type: Int) {
        var loadImage = LoadImage()
        loadImage.execute(type)
        progressBar.visibility = View.VISIBLE
        loadImage.setListener {
            if (null != it) {
                iv_showDownImage.setImageBitmap(it)
                progressBar.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
            }


        }
    }

    private var mHandle: Handler = Handler() {
        if (it.arg1 == 0) {
            var bitMap: Bitmap = BitmapFactory.decodeStream(it.obj as InputStream?);
            iv_showDownImage.setImageBitmap(bitMap)
        } else if (it.arg1 == 1) {

        }

        false
    };


    class LoadImage : AsyncTask<Int, Objects, Bitmap>() {
        lateinit var SuccessListener: (Bitmap) -> Unit

        fun setListener(successListener: (Bitmap) -> Unit) {
            this.SuccessListener = successListener

        }

        override fun doInBackground(vararg params: Int?): Bitmap? {
            var bitmap: Bitmap? = null;
            var urlStr = "http://wap.xiziwang.net/uploads/allimg/140717/34_140717204425_1.jpg"
            Log.e("TAG", "params====>" + params)
            when (params[0]) {
                0 -> bitmap = HttpClientLoadImage(urlStr);
                1 -> bitmap = HttpUrlConnectionLoad(urlStr)
                2 -> bitmap = OkHttpClentLoadImage(urlStr)

            }
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
//            super.onPostExecute(result)
            result?.let {
                SuccessListener.invoke(result)
            }
        }

        /**
         * HttpUrlConnection 请求图片
         */
        fun HttpUrlConnectionLoad(url: String): Bitmap {
            var bitmap: Bitmap? = null
            var url = URL(url);
            var httpReq: HttpURLConnection;
            httpReq = url.openConnection() as HttpURLConnection;
            httpReq.requestMethod = "GET"
            httpReq.readTimeout = 5000;
            httpReq.connectTimeout = 3000;
            var responseCode: Int = httpReq.responseCode
            Log.e("TAG", "responseCode====" + responseCode)
            if (responseCode == 200) {
                var input: InputStream = httpReq.inputStream
                Log.e("TAG", "input====" + input)
                bitmap = BitmapFactory.decodeStream(input)
                return bitmap
            } else {
                Log.e("TAG", "responseCode====" + responseCode)

            }
            return bitmap!!;
        }

        /**
         * HttpClient 请求数据
         */
        fun HttpClientLoadImage(url: String): Bitmap {
            var bitmap: Bitmap? = null
            var httpclient: HttpClient = DefaultHttpClient()
            var httpGet: HttpGet = HttpGet(url)
            var httpResPonse: HttpResponse = httpclient.execute(httpGet)
            if (httpResPonse.statusLine.statusCode == 200) {
                var httpEntity = httpResPonse.entity
                var inputStream = httpEntity.content
                bitmap = BitmapFactory.decodeStream(inputStream)
                return bitmap
            }
            return bitmap!!
        }


        fun OkHttpClentLoadImage(url: String): Bitmap {
            var bitmap: Bitmap? = null
            //OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
            var okhttp = OkHttpClient();
            var request = Request.Builder().url(url).build();
            var response: Response = okhttp.newCall(request).execute()


            Log.d("TAG", "OkhttpClient---" + response.code)
            if (response.code == 200) {
                var inputStream: InputStream = response.body!!.byteStream()

                bitmap = BitmapFactory.decodeStream(inputStream)
                return bitmap
            }

            return bitmap!!
        }
    }


}