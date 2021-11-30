package cn.edu.swu.oldcontact.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import cn.edu.swu.oldcontact.R;


public class BottomDialog extends BottomSheetDialogFragment {

    TextView mDlgLeftBtn;
    TextView mDlgRightBtn;
    onDlgLeftBtnClickListener mLeftListener;
    onDlgRightBtnClickListener mRightListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false);
        initView(view);

        return view;
    }
    public interface onDlgLeftBtnClickListener{
        public void onClick();
    }

    public void setOnDlgLeftBtnClickListener(onDlgLeftBtnClickListener listener){
        mLeftListener = listener;
    }
    public interface onDlgRightBtnClickListener{
        public void onClick();
    }

    public void setOnDlgRightBtnClickListener(onDlgRightBtnClickListener listener){
        mRightListener = listener;
    }

    private void initView(View view) {
        mDlgLeftBtn = view.findViewById(R.id.dlg_left_btn);
        mDlgRightBtn = view.findViewById(R.id.dlg_right_btn);

        mDlgLeftBtn.setOnClickListener(v->{
            mLeftListener.onClick();
        });

        mDlgRightBtn.setOnClickListener(v->{
            mRightListener.onClick();
        });

    }


}




