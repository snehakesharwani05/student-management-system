package service;

public class AutoSaveThread implements Runnable {
    private final IStudentService service;
    private volatile boolean running = true;

    public AutoSaveThread(IStudentService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Auto-save snapshots every 30 seconds
                Thread.sleep(30000);
                service.saveToFile();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}