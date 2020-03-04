package fr.iutlens.mineclick;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class RefreshHandler3 extends Handler {

    WeakReference<TimerAction3> weak;

    public RefreshHandler3(TimerAction3 animator){
        weak = new WeakReference(animator);
    }

    @Override
    public void handleMessage(Message msg) {
        if (weak.get() == null) return;
        weak.get().updateTimer3();
    }

    public void scheduleRefresh(long delayMillis) {
        this.removeMessages(0);
        sendMessageDelayed(obtainMessage(0), delayMillis);
    }

    public boolean isRunning() {
        return this.hasMessages(0);
    }
}