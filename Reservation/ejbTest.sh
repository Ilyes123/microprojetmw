#!/bin/bash
cd ~/microprojetmiddleware/Reservation/Reservation

ant clean
ant build
ant deploy


java client.AdministrationClient
java client.MailBoxClient


ant undeploy
ant clean
