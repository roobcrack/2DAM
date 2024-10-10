@echo off
setlocal & pushd
set APP_ENTRY=com.ruben.AppEntry
set BASE=%~dp0
set CP=%BASE%\classes;%BASE%\lib\*
title Running ProgramacionFuncional-0.0.1-SNAPSHOT powered by actframework-1.9.2
java -server -Xms128M -Xmx1G -XX:MaxPermSize=128M  -Dapp.mode=prod -Dprofile=%PROFILE% -cp "%CP%" %APP_ENTRY%
endlocal & popd
