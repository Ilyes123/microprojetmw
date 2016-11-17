require 'net/http'

base_url = "http://localhost:9876/mail/"
service = "mail_box"


# Read unread messages of user number 1
res = Net::HTTP.get(URI("#{base_url}#{service}/1/unread_messages"));

puts res.body

# Post a news message 
Net::HTTP.get(URI("#{base_url}#{service}/send_news"), "user_name"=>"user", "msg"=> "hello World")
