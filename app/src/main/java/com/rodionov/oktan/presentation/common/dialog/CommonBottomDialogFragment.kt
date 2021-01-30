package com.rodionov.oktan.presentation.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.rodionov.oktan.R
import com.rodionov.oktan.app.extension.gone
import com.rodionov.oktan.app.extension.setData
import com.rodionov.oktan.app.extension.show
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.presentation.common.delegates.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.dialog_bottom_sheet.*
import kotlinx.android.synthetic.main.item_second_level_parameters.*

class CommonBottomDialogFragment : BottomSheetDialogFragment() {

    private var listener: BottomSheetDialogListener? = null
    private var gasolineStation: GasolineStation? = null

    private val bottomDialogAdapter by lazy {
        ListDelegationAdapter(
//            bottomDialogDelegate(clickListener)
                selectFuelStationTypeAdapter(),
                itemFirstLevelAdapter(),
                secondLevelParametersItemAdapter()
        )
    }

    private val clickListener: (ItemDialog) -> Unit = {
        listener?.onItemDialogClick(it)
        dismiss()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener = targetFragment as? BottomSheetDialogListener
//        val items = arguments?.getSerializable(ARG_ITEMS) as? List<ItemDialog>
//        val title = arguments?.getString(ARG_TITLE)
//        tvTitleBottomSheet.text = title
        vpFuelStationCreate.adapter = bottomDialogAdapter
//        rvBottomSheetDialog.layoutManager = LinearLayoutManager(activity)
//        rvBottomSheetDialog.adapter = bottomDialogAdapter

//        vpFuelStationCreate.isUserInputEnabled = false

        ivVpBack.setOnClickListener {
            vpFuelStationCreate.setCurrentItem(vpFuelStationCreate.currentItem - 1, true)
        }
        ivVpForward.setOnClickListener {
            vpFuelStationCreate.setCurrentItem(vpFuelStationCreate.currentItem + 1, true)
        }
        bottomDialogAdapter.setData(listOf(Unit, FirstLevelParameters(), SecondLevelParameters()))
        vpFuelStationCreate.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        when (position) {
                            0 -> {
                                ivVpBack.gone()
                                ivVpForward.show()
                            }
                            1 -> {
                                ivVpBack.show()
                                ivVpForward.show()
                            }
                            2 -> {
                                ivVpBack.show()
                                ivVpForward.gone()
                            }
                        }
                    }

                }
        )
//        vpFuelStationCreate.setCurrentItem(1, true)
    }


    interface BottomSheetDialogListener {
        fun onItemDialogClick(itemDialog: ItemDialog)
    }

    companion object {
        private const val ARG_ITEMS = "items"
        private const val ARG_TITLE = "title"

        fun newInstance(items: List<ItemDialog> = emptyList(), title: String = "") =
                CommonBottomDialogFragment().apply {
                    arguments = bundleOf(
                            ARG_ITEMS to items,
                            ARG_TITLE to title
                    )
                }
    }
}