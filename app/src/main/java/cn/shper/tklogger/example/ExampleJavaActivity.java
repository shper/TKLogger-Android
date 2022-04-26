package cn.shper.tklogger.example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.shper.tklogger.javakit.TKLogger;

/**
 * Author: Shper
 * Email: me@shper.cn
 * Version: V0.1 2022/3/9
 */
public class ExampleJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TKLogger.d("");
    }

}
