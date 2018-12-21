package vn.lepha_khtn.androidhometest.main

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item_keyword.view.*
import vn.lepha_khtn.androidhometest.R
import vn.lepha_khtn.androidhometest.utils.AppConstants
import vn.lepha_khtn.androidhometest.utils.CommonUtils
import vn.lepha_khtn.androidhometest.utils.FileUtils

/**
 * Created by JB Pha Le
 */
class KeywordAdapter(context: Context) : RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {

    private val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
    private val type = object : TypeToken<List<String>>() {}.type
    private val gson = builder.create()
    private var models = gson.fromJson<List<String>>(
        FileUtils.loadJSONFromAsset(context, AppConstants.DATABASE_KEYWORDS), type
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var keyword = models[position].trim()

        //If the keyword is more than one word, then display in two lines.
        val spaceTotal = keyword.filter { it == ' ' }.count()
        if (spaceTotal > 0) {
            val expectedSpaceIndex = spaceTotal / 2 + if (spaceTotal % 2 == 0) 0 else 1
            var spaceIndex = 0
            for ((index, char) in keyword.toCharArray().withIndex()) {
                if (char == ' ') {
                    spaceIndex++
                    if (spaceIndex == expectedSpaceIndex) {
                        keyword = StringBuilder(keyword).insert(index, "\n").toString()
                        break
                    }
                }
            }
        }
        holder.txtKeyword?.text = keyword

        //Background color is random.
        val drawable = holder.txtKeyword?.background as GradientDrawable
        drawable.setColor(CommonUtils.getRandomColor())
        holder.txtKeyword.background = drawable
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtKeyword: TextView? = itemView.txtKeyword
    }
}

