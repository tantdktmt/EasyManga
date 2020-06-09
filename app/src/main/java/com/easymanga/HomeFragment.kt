package com.easymanga

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.core.BaseFragment
import com.easymanga.data.model.Channel
import com.easymanga.data.network.ApiManager
import com.easymanga.util.Constant
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var apiManager: ApiManager
    var test = "fawejfl"

    override fun onAttach(context: Context) {
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        GetChannelsTask().execute()
    }
    // TESTING
    private inner class GetChannelsTask : AsyncTask<Void, Void, List<Channel>?>() {

        override fun doInBackground(vararg params: Void?): List<Channel>? {
            return apiManager.getChannels()
        }

        override fun onPostExecute(result: List<Channel>?) {
            if (Constant.IS_DEBUG_MODE) {
                Log.d(Constant.LOG_TAG, "channel=$result")
            }
        }
    }
    // TESTING
}
