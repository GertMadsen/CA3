#!/bin/sh
DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$DIR"

SERVICE_NAME="Car Rental Service"
PATH_TO_JAR=../lib/car-rental-service-0.0.2.jar
PATH_TO_CONF=../conf/
PID_PATH_NAME=/tmp/car_rental_service-pid
case $1 in
    start)
        if which java > /dev/null
        then
            version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f2)
            if [ $version -lt "8" ]; then
                echo 'Java 8 or higher is required'
                exit 1
            fi
        else
            echo 'Java runtime is required.'
            exit 1
        fi
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar $PATH_TO_JAR --spring.config.name=application --spring.config.location=$PATH_TO_CONF /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
            exit 1
        else
            echo "$SERVICE_NAME is already running ..."
            exit 1
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
            exit 1
        else
            echo "$SERVICE_NAME is not running ..."
            exit 1
        fi
    ;;
    restart)
        if which java > /dev/null
        then
            version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f2)
            if [ $version -lt "8" ]; then
                echo 'Java 8 or higher is required'
                exit 1
            fi
        else
            echo 'Java runtime is required.'
            exit 1
        fi
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar $PATH_TO_JAR --spring.config.name=application --spring.config.location=$PATH_TO_CONF /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
            exit 1
        else
            echo "$SERVICE_NAME is not running ..."
            exit 1
        fi
    ;;
esac
echo "Usage: ./service.sh [start][stop][restart]"
