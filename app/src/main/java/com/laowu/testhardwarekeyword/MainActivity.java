package com.laowu.testhardwarekeyword;

import android.hardware.input.InputManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etSoftInput;
    private EditText etHardInput;
    private Button btnSoftInput;
    private Button btnHardInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        lisenHardWare();
    }

    private void initView() {
        etSoftInput = (EditText) findViewById(R.id.et_soft_input);
        etHardInput = (EditText) findViewById(R.id.et_hard_input);
        btnSoftInput = (Button) findViewById(R.id.btn_soft_input);
        btnHardInput = (Button) findViewById(R.id.btn_hard_input);

        btnSoftInput.setOnClickListener(this);
        btnHardInput.setOnClickListener(this);
    }

    private void lisenHardWare() {
        //打印USB设备
        UsbManager usbManager = (UsbManager) getSystemService(USB_SERVICE);
        HashMap<String, UsbDevice> deviceLists = usbManager.getDeviceList();
        for (String key : deviceLists.keySet()) {
            Toast.makeText(this, deviceLists.get(key).getDeviceName(), Toast.LENGTH_LONG).show();
        }
        //监听硬件输入信息
        InputManager inputManager = (InputManager) this.getSystemService(INPUT_SERVICE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_soft_input:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                inputMethodManager.setInputMethod(null, "com.android.inputmethod.latin/.LatinIME");
                etSoftInput.setText("test");
                List<InputMethodInfo> methodInfoList = inputMethodManager.getInputMethodList();
                for (int i=0; i<methodInfoList.size(); i++) {
                    toast(methodInfoList.get(i).getId());
                }
                break;
            case R.id.btn_hard_input:

                break;
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
