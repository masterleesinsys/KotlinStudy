package fly.kotlinstudy.cm.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import fly.kotlinstudy.cm.R
import org.json.JSONException
import org.json.JSONObject
import org.xutils.common.Callback
import org.xutils.common.util.DensityUtil
import org.xutils.http.RequestParams
import org.xutils.image.ImageOptions
import org.xutils.x
import java.io.File

@Suppress("JoinDeclarationAndAssignment", "UNUSED_PARAMETER", "LocalVariableName")
class HttpHelper private constructor() {
    private val handler: Handler
    private var options: ImageOptions? = null

    init {
        handler = Handler(Looper.getMainLooper())
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param paramMap
     * @param callBack
     */
    operator fun get(url: String, paramMap: Map<String, String>?, context: Context, callBack: XCallBack) {
        val params = RequestParams(url)
        if (paramMap != null && !paramMap.isEmpty()) {
            for ((key, value) in paramMap) {
                params.addQueryStringParameter(key, value)
            }
        }

        x.http().get(params, object : Callback.CommonCallback<String> {
            override fun onSuccess(result: String) {
                onSuccessResponse(result, callBack)
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                ex.printStackTrace()
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {

            }

            override fun onFinished() {

            }
        })

    }

    /**
     * 异步post请求
     *
     * @param url
     * @param maps
     * @param callback
     */
    fun post(url: String, maps: Map<String, String>?, context: Context, callback: XCallBack) {
        val params = RequestParams(url)
        val js_request = JSONObject()//服务器需要传参的json对象
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                //                params.addBodyParameter(entry.getKey(), entry.getValue());
                try {
                    js_request.put(key, value)//根据实际需求添加相应键值对
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        params.isAsJsonContent = true
        params.bodyContent = js_request.toString()

        x.http().post(params, object : Callback.CommonCallback<String> {

            override fun onSuccess(result: String) {
                onSuccessResponse(result, callback)

            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {

            }

            override fun onFinished() {

            }
        })
    }

    fun post(url: String, maps: Map<String, Any>?, context: Context, `is`: Boolean?, callback: XCallBack) {
        val params = RequestParams(url)
        val js_request = JSONObject()//服务器需要传参的json对象
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                //                params.addBodyParameter(entry.getKey(), entry.getValue());
                try {
                    js_request.put(key, value)//根据实际需求添加相应键值对
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        params.isAsJsonContent = true
        params.bodyContent = js_request.toString()

        x.http().post(params, object : Callback.CommonCallback<String> {

            override fun onSuccess(result: String) {
                onSuccessResponse(result, callback)
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {

            }

            override fun onFinished() {

            }
        })
    }


    /**
     * 带缓存数据的异步 get请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param callback
     */
    fun getCache(url: String, maps: Map<String, String>?, pnewCache: Boolean, context: Context, callback: XCallBack) {

        val params = RequestParams(url)
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                params.addQueryStringParameter(key, value)
            }
        }

        x.http().get(params, object : Callback.CacheCallback<String> {
            override fun onSuccess(result: String) {
                onSuccessResponse(result, callback)
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {}

            override fun onFinished() {

            }

            override fun onCache(result: String): Boolean {
                var newCache = pnewCache
                if (newCache) {
                    newCache = !newCache
                }
                if (!newCache) {
                    newCache = !newCache
                    onSuccessResponse(result, callback)
                }
                return newCache
            }
        })
    }

    /**
     * 带缓存数据的异步 post请求
     *
     * @param url
     * @param maps
     * @param pnewCache
     * @param callback
     */
    fun postCache(url: String, maps: Map<String, String>?, pnewCache: Boolean, context: Context, callback: XCallBack) {
        val params = RequestParams(url)
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                params.addBodyParameter(key, value)
            }
        }

        x.http().post(params, object : Callback.CacheCallback<String> {
            override fun onSuccess(result: String) {
                onSuccessResponse(result, callback)
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {

            }


            override fun onFinished() {

            }

            override fun onCache(result: String): Boolean {
                var newCache = pnewCache
                if (newCache) {
                    newCache = !newCache
                }
                if (!newCache) {
                    newCache = !newCache
                    onSuccessResponse(result, callback)
                }
                return newCache
            }
        })
    }


    /**
     * 正常图片显示
     *
     * @param iv
     * @param url
     * @param option
     */
    /**/
    fun bindCommonImage(iv: ImageView, url: String, option: Boolean) {
        if (option) {
            options = ImageOptions.Builder().setLoadingDrawableId(R.drawable.loading).setFailureDrawableId(R.drawable.loadfailed).build()
            x.image().bind(iv, url, options)
        } else {
            x.image().bind(iv, url)
        }
    }

    /**
     * 圆形图片显示
     *
     * @param iv
     * @param url
     * @param option
     */
    /* */
    fun bindCircularImage(iv: ImageView, url: String, option: Boolean) {
        if (option) {
            options = ImageOptions.Builder().setLoadingDrawableId(R.drawable.loading).setFailureDrawableId(R.drawable.loadfailed).setCircular(true).build()
            x.image().bind(iv, url, options)
        } else {
            x.image().bind(iv, url)
        }
    }

    /**
     * 圆形图片显示,不用缓存
     *
     * @param iv
     * @param url
     */
    /* */
    fun bindCircularImageNOcache(iv: ImageView, url: String) {
        options = ImageOptions.Builder().setLoadingDrawableId(R.drawable.loading)
                .setFailureDrawableId(R.drawable.loadfailed).setRadius(DensityUtil.dip2px(5f)).setUseMemCache(false).build()
        x.image().bind(iv, url, options)
    }


    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param callback
     */
    fun upLoadFile(url: String, maps: Map<String, String>?, file: Map<String, File>?, context: Context, callback: XCallBack) {
        val params = RequestParams(url)
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                params.addBodyParameter(key, value)
            }
        }
        if (file != null) {
            for ((key, value) in file) {
                params.addBodyParameter(key, value.absoluteFile)
            }
        }
        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.isMultipart = true

        x.http().post(params, object : Callback.CommonCallback<String> {
            override fun onSuccess(result: String) {
                onSuccessResponse(result, callback)
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {}

            override fun onFinished() {

            }
        })

    }


    /**
     * 文件下载
     *
     * @param url
     * @param maps
     * @param callBack
     */
    fun downLoadFile(url: String, maps: Map<String, String>?, context: Context, callBack: XDownLoadCallBack?) {

        val params = RequestParams(url)
        if (maps != null && !maps.isEmpty()) {
            for ((key, value) in maps) {
                params.addBodyParameter(key, value)
            }
        }
        params.isAutoRename = true// 断点续传
        params.saveFilePath = SAVE_FILE_PATH

        x.http().post(params, object : Callback.ProgressCallback<File> {
            override fun onSuccess(result: File) {
                handler.post {
                    callBack?.onResponse(result)
                }
            }

            override fun onError(ex: Throwable, isOnCallback: Boolean) {
                Toast.makeText(Fly.app,"远程接口异常！", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(cex: Callback.CancelledException) {}

            override fun onFinished() {
                handler.post {
                    callBack?.onFinished()
                }
            }

            override fun onWaiting() {

            }

            override fun onStarted() {

            }

            override fun onLoading(total: Long, current: Long, isDownloading: Boolean) {
                handler.post {
                    callBack?.onLoading(total, current, isDownloading)
                }
            }
        })

    }


    /**
     * 异步get请求返回结果,json字符串
     *
     * @param result
     * @param callBack
     */
    private fun onSuccessResponse(result: String, callBack: XCallBack?) {
        handler.post {
            callBack?.onResponse(result)
        }
    }


    interface XCallBack {
        fun onResponse(result: String)
    }


    interface XDownLoadCallBack : XCallBack {
        fun onResponse(result: File)

        fun onLoading(total: Long, current: Long, isDownloading: Boolean)

        fun onFinished()
    }

    /**
     * 单例模式
     */
    companion object {

        private val SAVE_FILE_PATH = ""

        fun getInstance(): HttpHelper? {
            var instance: HttpHelper? = null
            if (instance == null) {
                synchronized(HttpHelper::class.java) {
                    if (instance == null) {
                        instance = HttpHelper()
                    }
                }
            }
            return instance
        }
    }
}
