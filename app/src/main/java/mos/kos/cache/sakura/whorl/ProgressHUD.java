package mos.kos.cache.sakura.whorl;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import mos.kos.cache.R;
import mos.kos.cache.tool.UTxt;


/**
 * Created by ZeroProject on 2016/8/29 0029 16:24
 * Email:ZeroProject@foxmail.com
 * 圆形转圈的进度条
 */
public class ProgressHUD extends Dialog {
    private TextView tMsg;
    private WhorlView wlv;
    private View layRoot;

    public ProgressHUD(Context context) { // NO_UCD (unused code)
        this(context, 0);
    }

    public ProgressHUD(Context context, int theme) {
        super(context, theme);
    }


    public void setMessage(String msg) {
        if (UTxt.isEmpty(msg)) {
            tMsg.setVisibility(View.GONE);
        } else {
            tMsg.setText(msg);
        }
        tMsg.invalidate();
    }


    public void showDialog() {
        if (!isShowing()) {
//            setContentView(getLayoutInflater().inflate(R.layout.lay_progress_pop, null),
//                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setContentView(R.layout.lay_progress_pop);
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.width = UZDevice.getScreenWidth(getContext());
//            params.height = UZDevice.getScreenHeight(getContext());
//            getWindow().setAttributes(params);
            tMsg = findViewById(R.id.progress_msg);
            wlv = findViewById(R.id.progress_wv_p);
            layRoot = findViewById(R.id.lay_progress_root);
            setMessage(null);
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            show();
            wlv.start();
        }
    }

    public void showDialog(String msg) {
        if (!isShowing()) {
            setContentView(R.layout.lay_progress_pop);
            tMsg = findViewById(R.id.progress_msg);
            wlv = findViewById(R.id.progress_wv_p);
            layRoot = findViewById(R.id.lay_progress_root);
            setMessage(msg);
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            show();
            wlv.start();
        }
    }

    public void hideDialog() {
        layRoot.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        new Handler().postDelayed(() -> {
            dismiss();
            wlv.stop();
        }, 200);
    }
}
