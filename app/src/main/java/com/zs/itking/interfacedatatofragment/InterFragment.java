package com.zs.itking.interfacedatatofragment;

import android.app.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * created by on 2021/11/3
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-11-03-16:49
 */
public class InterFragment extends Fragment {
    //定义用来与MainActivity交互
    private FragmentInteraction fragmentInteraction;
    //获取InterFragment的控件
    private EditText edit;


    /**
     * 当InterFragment被加载到MainActivity时回调
     * 在Fragment 和 Activity 建立关联是调用
     *
     * @param activity 宿主MainActivity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 这个方法是用来确认当前的Activity容器是否已经继承了该接口，如果没有将抛出异常
        if (activity instanceof FragmentInteraction) {
            fragmentInteraction = (FragmentInteraction) activity; // 获取到宿主activity并赋值
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    /**
     * 当Fragment 创建视图时调用
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inter, container, false);
        //初始化控件
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view 控件
     */
    private void initView(View view) {
        edit = view.findViewById(R.id.edit);
        Button button = view.findViewById(R.id.button);

        //点击开始传递数据
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当前InterFragment的宿主Activity只要实现此接口，那么InterFragment就能有权利调用该接口方法
                //MainActivity实现FragmentInteraction接口，那么process()才是具体的方法，没实现就是抽象的方法
                fragmentInteraction.process(edit.getText().toString());
            }
        });
    }

    /**
     * Fragment生命周期最后一个阶段，在这个阶段把传递进来的宿主Activity对象释放掉
     * 避免资源未清理，finish宿主Activity时出现未知错误
     */
    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInteraction = null;
    }
}
