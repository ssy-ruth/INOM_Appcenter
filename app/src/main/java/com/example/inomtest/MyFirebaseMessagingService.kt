package com.example.inomtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        // 메시지에 데이터 페이로드가 포함되어 있는지 확인. 페이로드 : 전송된 데이터를 의미.
        // 데이터 값이 있는지 없는지 확인할 때 쓰임.
        if (remoteMessage.data.isNotEmpty()) {

            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            if (true) {
                // 데이터를 처리하는 데 10초 이상이 걸리면 workManager를 사용.
                scheduleJob()
            } else {
                // 10초 이내에 시작하면 아래 메서드를 실행.
                handleNow()
            }
        }

        // 메시지에 알림 페이로드가 포함되어 있는지 확인.
        remoteMessage.notification?.let {
            it.body?.let { it1 -> sendNotification(it1) }
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }

    // FirebaseInstanceIdService는 이제 사라짐. 이제 이걸 사용.
    // FCM 등록 토큰이 업데이트되면 호출.
    // 토큰이 처음 생성될 때 여기에서 토큰을 검색.
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // 이 앱 인스턴스에 메시지를 보내려는 경우나 서버 측에서 이 앱 구독을 관리한다면,
        // FCM 등록 토큰을 앱 서버에 추가.

        sendRegistrationToServer(token)
    }

    // 메시지 페이로드가 있을 때 실행되는 메서드(10초 이상 걸릴 때 호출.)
    // WorkManager를 사용하여 비동기 작업을 예약.
    private fun scheduleJob() {
        // [START dispatch_job]
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).beginWith(work).enqueue()
    }

    // 메시지 페이로드가 있을 때 실행되는 메서드(10초 이내로 걸릴 때 호출.)
    // BroadcastReceivers에 할당 된 시간을 처리.
    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    // 타사 서버에 토큰을 유지해주는 메서드.
    // 사용자의 FCM등록 토큰을 서버 측 계정에 연결하려면 이 방법을 사용.
    // 응용 프로그램에서 유지 관리를 함.
    // 파라미터에 들어있는 토큰은 새로운 토큰.
    private fun sendRegistrationToServer(token: String?) {
        // 이 메서드를 구현하여 앱 서버에 토큰을 보냄.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }

    // 수신된 FCM 메시지를 포함하는 간단한 알림을 만들고 표시.
    // 파라미터에 있는 messageBody에는 FCM 메시지 본문이 담겨져 있음.
    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("Notification", "노티피케이션이다 임마")

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = "Noti_channelID"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(getString(R.string.fcm_message))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 안드로이드 오레오 알림채널이 필요하기 때문에 넣음.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}