package com.example.weibo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weibo.R;
import com.example.weibo.test.PostRequest_Interface;
import com.example.weibo.utils.Translation;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTestActivity extends AppCompatActivity {

    TextView result;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        result = (TextView) findViewById(R.id.tv_result);
        input = (EditText) findViewById(R.id.et_input);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //request(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                request(s.toString());
            }
        });
       //request();
    }

    public void request(String str) {
        //步骤四：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //步骤五：创建网络请求接口的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);
        //对发送请求进行封装（设置需要翻译的内容）
        Call<Translation> call = request.getCall(str);
        //步骤六：发送网络请求（异步）
        call.enqueue(new Callback<Translation>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                //步骤七：处理返回的数据结果：输出翻译的内容
                Log.d("RetrofitTestActivity",response.body().getTranslateResult().get(0).get(0).getTgt());
                result.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.d("RetrofitTestActivity","请求失败");
                System.out.println(t.getMessage());
            }
        });
    }
}
