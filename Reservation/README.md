# Rest interface description

* The application's base URI is : `http://localhost:9876/mail`

We chose to implement the mail box manager in REST and deploy it to `/mail_box`

## The interface 
POST    `/add`  (query params : [`box_name`, `user_id`])  creates a new mail box 
DELETE  `/box/{box_id}`                                   removes a mail box
GET     `/{user_id}/unread_messages`                      returns all unread messages of user and marks them as read
GET     `/{user_id}/messages`                             returns all user's messages
DELETE  `/{user_id}/messages/{message_id}`                delete the user's message
DELETE  `/{user_id}/read_messages`                        delete's a users all read msgs
POST    `/send_news` (query params: [`user_name`, `msg`]) posts a new message to the news box
