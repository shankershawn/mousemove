@echo off

:loop
java -jar mousemove-1.0.0.jar 15000

IF %ERRORLEVEL% EQU 1 (
    echo Program exited with code 1. Restarting...
    timeout /t 2 >nul
    goto loop
)

echo Program exited with code %ERRORLEVEL%. Stopping.
exit /b %ERRORLEVEL%