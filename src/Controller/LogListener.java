package Controller;

import java.util.EventListener;

public interface LogListener extends EventListener {
public void onLogWaiting(String log);
}
