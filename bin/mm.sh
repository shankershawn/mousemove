#!/bin/bash

while true
do
    java -jar mousemove-1.0.0.jar 15000
    exit_code=$?

    if [ $exit_code -eq 1 ]; then
        echo "Program exited with code 1. Restarting..."
        sleep 2
    else
        echo "Program exited with code $exit_code. Stopping."
        exit $exit_code
    fi
done