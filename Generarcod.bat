@echo off
setlocal enabledelayedexpansion

:: 1. Configuración de salida
for %%I in ("%cd%") do set "FolderName=%%~nxI"
set "output=DATA_!FolderName!.txt"
set "tempMedia=media_list.tmp"

:: Limpieza previa
if exist "!tempMedia!" del "!tempMedia!"
if exist "!output!" del "!output!"

echo [ANALISIS DE PROYECTO: !FolderName!] > "!output!"
echo [ESTRUCTURA DE DIRECTORIOS] >> "!output!"
tree /f /a >> "!output!"
echo ================================================== >> "!output!"

:: 2. Procesar archivos recursivamente
for /r %%i in (*.*) do (
    set "fullPath=%%i"
    set "relPath=!fullPath:%cd%\=!"
    set "filename=%%~nxi"
    set "ext=%%~xi"
   
    :: EXCLUSIONES: No procesar herramientas ni archivos generados
    set "skip=0"
    if /I "!ext!"==".bat" set "skip=1"
    if /I "!ext!"==".tmp" set "skip=1"
    if /I "!ext!"==".class" set "skip=1"
    if /I "!filename!"=="!output!" set "skip=1"

    if "!skip!"=="0" (
        set "isCode=0"
        
        :: Filtro manual de extensiones para mayor compatibilidad
        if /I "!ext!"==".java" set "isCode=1"
        if /I "!ext!"==".properties" set "isCode=1"
        if /I "!ext!"==".xml" set "isCode=1"
        if /I "!ext!"==".lua" set "isCode=1"
        if /I "!ext!"==".json" set "isCode=1"
        if /I "!ext!"==".md" set "isCode=1"

        if "!isCode!"=="1" (
            echo Escribiendo: !relPath!
            echo [FILE: !relPath!] >> "!output!"
            type "%%i" >> "!output!"
            echo. >> "!output!"
            echo [END: !relPath!] >> "!output!"
            echo .................................................. >> "!output!"
        ) else (
            echo !relPath! >> "!tempMedia!"
        )
    )
)

:: 3. Consolidar lista de recursos y limpieza
echo. >> "!output!"
echo [RECURSOS ADICIONALES Y ASSETS] >> "!output!"
if exist "!tempMedia!" (
    type "!tempMedia!" >> "!output!"
    del "!tempMedia!"
)

echo.
echo Proceso terminado.
echo Archivo generado: !output!
pause