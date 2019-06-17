@echo off
title MacRP Startup Program
cls
:: 获取参数,此脚本假设有两个参数
:: set arg1=%1%
:: set arg2=%2%
:: set arg3=%3%
:: set arg4=%4%
set arg1=C:\Users\dguo\Desktop\myjenkins
set arg2=bpm-service-1.0-20190517.062657-1
set arg3=file-service-1.0-20190517.062657-1
set arg4=isump-service-1.0-20190517.062657-1

::统计参数个数
set /a cnt=0
:loop
if not "%1"=="" (set /a cnt+=1&shift /1&goto :loop)

::打印环境信息
echo System Information:
echo ******************************
echo COMPUTERNAME=%COMPUTERNAME%
echo OS=%OS%
echo ParamPath=%arg1%
echo ParamCnt=%cnt%
echo JAVA_HOME=%JAVA_HOME%
echo CURRENT_DATE=%date% %time%:~0,8%
echo ******************************

::跳转到指定的repo目录
:: cd %1%
:: start cmd /k "java -jar selenium-server-standalone-3.5.2.jar"
:: start cmd /k "java -jar selenium-server-standalone-3.5.1.jar"

:: C:\Users\dguo\Desktop\myjenkins
:: com\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\bpm-service-1.0-20190517.062657-1.jar

:: 发布基础服务 file bpm isump
::start cmd /k "java -jar com\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\bpm-service-1.0-20190517.062657-1.jar"

:: set pathParts=\com

:: 处理参数2
:: echo param=%arg2%
:: echo path=%arg1%%pathParts%\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\%param%.jar

set param=%arg2%
if "%param:bpm=%" neq "%param%" (
	echo start bpm-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:file=%" neq "%param%" (
	echo start file-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\file-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:isump=%" neq "%param%" (
	echo start isump-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\isump-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

:: 处理参数3
:: echo param=%arg3%

set param=%arg3%
if "%param:bpm=%" neq "%param%" (
	echo start bpm-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:file=%" neq "%param%" (
	echo start file-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\file-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:isump=%" neq "%param%" (
	echo start isump-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\isump-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

:: 处理参数4
:: echo param=%arg4%

set param=%arg4%
if "%param:bpm=%" neq "%param%" (
	echo start bpm-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\bpm-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:file=%" neq "%param%" (
	echo start file-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\file-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)

if "%param:isump=%" neq "%param%" (
	echo start isump-service
	start cmd /k "java -jar %arg1%\com\deloitte\gdc\oracle\isump-service\1.0-SNAPSHOT\%param%.jar"
) else (
	echo Process with parameter: %param%
)