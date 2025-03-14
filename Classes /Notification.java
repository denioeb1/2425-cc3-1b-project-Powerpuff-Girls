class Notification {
    private int notificationID;
    private int userID;
    private String message;
    private Date timestamp;

    public Notification(int notificationID, int userID, String message) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.timestamp = new Date();
    }

    public void sendNotification() {
        System.out.println("Notification sent: " + message);
    }

    public void viewNotifications() {
        System.out.println("Notification: " + message + " at " + timestamp);
    }
}
