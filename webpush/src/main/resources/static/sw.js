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

self.addEventListener('notiwin', function(event) {  //Notification을 클릭할 떄 이벤트를 정의합니다.
    event.notification.close();  // Notification을 닫습니다.
    event.waitUntil(clients.matchAll({  //같은 주소의 페이지가 열려있는 경우 focus
        type: 'window'
    }).then(function(clientList) {
        for (var i = 0; i < clientList.length; i++) {
            var client = clientList[i];
            if (client.url === '/' && 'focus' in client) {
                return client.focus();
            }
        }
        if (clients.openWindow) { //같은 주소가 아닌 경우 새창으로
            return clients.openWindow(event.notification.data);
        }
    }));
});

//
// self.registration.showNotification('📢 새 소식!', {
//     body: '이건 너만을 위한 메시지야.',
//     icon: '/img/bell.png',
//     data: { url: '/new-content' }
// });
