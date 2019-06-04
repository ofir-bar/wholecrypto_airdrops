# Airdrops by Wholecrypto Android App

Airdrops by WholeCrpyoto is a native Android app written in Java.

# Abstract
In terms of cryptocurrencies, airdrops are a way for cryptocurrency project owners to market themselfs. The tradeoff is simple:
Project owners will give small amount of their coin/token they develop to users.
In exchange, project owners may ask users for specific tasks.

Example: I am creating a project called "OfirCoin", which has a token/coin named "OFC" .
I decided to create an airdrop, limited to 100 users. For each user, I will distribute 50 OFC (equivalent to 5$) for every participant who likes my Facebook page, and join my Telegram channel.

# Features
The app displays a list of cryptocurrency airdrops. Users browse airdrops, mark airdrops as done, save airdrops for "to-do" later & more. The app notifies users once a new airdrop is submitted (can be turned off).
Future features include connecting their crypto wallet to sum how much money they earned, commenting on airdrops and flagging airdrops as scams.

Cryptocurrency Owners can submit their airdrop through the Android app, iOS app or the web, which will only be shown to users after admin approves it in the database.


# Development Environment
The app currently written in Java and uses the Gradle build system. </br>
Firebase Real Time DB to store airdrops, and synchronize any changes to the db in real time.</br>
Firebase authentication for user registration (gmail and regular email).</br>
Firebase remote control for Terms & Service.</br>
As backend and to save user specific data I use Google Cloud Functions with Typescript.

# Screenshots

<div align="center">
    <img src="https://i.imgur.com/QFudc6o.png" width="250px"</img>
    <img src="https://i.imgur.com/lT5wvl3.png" width="250px"</img>
    <img src="https://i.imgur.com/mwj0mm9.png" width="250px"</img> 
</div>


# Download the app on Google Play

<a href='https://play.google.com/store/apps/details?id=com.wholecrypto.airdrops&rdid=com.wholecrypto.airdrops&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height = "63" width = "162" /></a>


# License
```
Copyright 2018 Ofir Bar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
