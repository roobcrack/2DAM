@echo off
setlocal & pushd
set APP_ENTRY=${app.entry}
set BASE=%~dp0
set CP=%BASE%\classes;%BASE%\lib\*
title Running ${project.artifactId}-${project.version} powered by actframework-${act.version}
javaw -server -Xms${vmargs.heap.init} -Xmx${vmargs.heap.max} -XX:MaxPermSize=128M ${vmargs.extended} -Dapp.mode=prod -Dprofile=%PROFILE% -cp "%CP%" %APP_ENTRY%
endlocal & popd
