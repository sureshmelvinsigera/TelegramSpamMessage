# Telegram Message Sender

Simple automatic message sender for telegram. Used tg web version and Selenium with firefox driver.  
Version: **v.1.1**

## Install
#### Linux
+ Copy this project by **git clone https://github.com/WILYR/TelegramSpamMessage.git**
+ Download Mozilla geckodriver from **https://github.com/mozilla/geckodriver** and unpackage into "/usr/local/bin" directory
+ Login into https://web.telegram.org/ and remember the name of your firefox profile("about:profiles")
#### Windows
- In progress....

## Configuring and fast start
Resources folder had a 3 files,which can be changed.
#### application.properties

 timeout - time between change message dir(rise up if you have a bad internet connection).  
 firefoxprofile - name of your Profile in Mozilla FireFox.  
 accountgenerator - if is true accounts will be generated automaticly, if false taken by accounts file
 
| Setting | default | 
|----------------|:---------:|
| timeout | 15 | 
| firefoxprofile | default-release | 
| accountgenerator | true |

 
 #### accounts
 File should contains only accounts by '@' + acc.name. Every account must be write in next line.  
 Example:  
 ```
 @asflwlegwel
 @jergegerge
 @jwflleweg
 ```
  #### message
  Message which must be sended to accounts from list above.
 
