/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musicplatform;

/**
 *
 * @author DHRUV
 */
public class UserData {

    private static int userId;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserData.userId = userId;
    }

    private static int subscriptionAmount;
    private static String subscriptionType;

    public static int getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public static String getSubscriptionType() {
        return subscriptionType;
    }

    public static void setSubscriptionPlan(int subscriptionAmount, String subscriptionType) {
        UserData.subscriptionAmount = subscriptionAmount;
        UserData.subscriptionType = subscriptionType;
    }
}
