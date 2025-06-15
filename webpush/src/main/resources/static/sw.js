self.addEventListener('push', event => {
    const data = event.data ? event.data.text() : 'No payload';
    event.waitUntil(
        self.registration.showNotification('알림', {
            body: data,
            icon: '/favicon.ico'
        })
    );
});

self.addEventListener('notificationclick', function(event) {
    event.notification.close();
    event.waitUntil(
        clients.openWindow('https://naver.com') // 클릭 시 이동할 URL
    );
});

self.registration.showNotification('📢 새 소식!', {
    body: '이건 너만을 위한 메시지야.',
    icon: '/img/bell.png',
    data: { url: '/new-content' }
});