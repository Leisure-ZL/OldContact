package cn.edu.swu.oldcontact.ui.contact;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import cn.edu.swu.oldcontact.R;

public class PickNumDialog extends BottomSheetDialogFragment {

    NumberPicker numberPicker;
    TextView mBtn;
    int mSelectNum;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_pick_num, container, false);
        numberPicker = view.findViewById(R.id.num_pick);
        mBtn = view.findViewById(R.id.enter_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(99);
        //设置默认的位置
        numberPicker.setValue(1);
        //这里设置为不循环显示，默认值为true
        numberPicker.setWrapSelectorWheel(false);
        //设置不可编辑
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //得到选择结果
                mSelectNum = newVal;
            }
        });

        mBtn.setOnClickListener(v->{
            this.dismiss();
        });

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        ((ContactPublishFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.main_activity_content))
        .mActPersonNum.setText(String.valueOf(mSelectNum));
    }
}
