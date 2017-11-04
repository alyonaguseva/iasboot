; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "AISBOOT"
#define MyAppVersion "1.0"
#define MyAppPublisher "VNIIG, Inc."
#define MyAppURL "http://www.rushydro.ru/"
#define MyAppExeName "run.cmd"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{9B1D1BBC-A637-4C38-B563-E0715DDD8ACC}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "russian"; MessagesFile: "compiler:Languages\Russian.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\yazik\IdeaProjects\iasboot\target\iasboot-1.0.jar"; DestDir: "{app}"; Flags: ignoreversion;
Source: "C:\Users\yazik\IdeaProjects\iasboot\src\main\resources\sql\*"; DestDir: "{app}\sql";
;Source: "C:\Users\yazik\IdeaProjects\iasboot\src\main\resources\application.properties"; DestDir: "{app}"; 
Source: "C:\Users\yazik\IdeaProjects\iasboot\src\main\resources\run.cmd"; DestDir: "{app}";
Source: "C:\Users\yazik\Downloads\jre-8u144-windows-x64.exe"; DestDir: "{app}\advanced"; AfterInstall: RunOtherInstaller('jre-8u144-windows-x64.exe'); 
Source: "C:\Users\yazik\Downloads\postgresql-9.6.5-1-windows-x64.exe"; DestDir: "{app}\advanced"; AfterInstall: RunOtherInstaller('postgresql-9.6.5-1-windows-x64.exe'); 
Source: "C:\Users\yazik\IdeaProjects\iasboot\src\main\resources\install.cmd"; DestDir: "{app}\advanced"; AfterInstall: RunOtherInstaller('install.cmd');

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon
Name: "{commonstartup}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"

[Registry]
Root: HKLM; Subkey: "SOFTWARE\Microsoft\Windows\CurrentVersion\Run"; ValueType: string; ValueName: "AIS"; ValueData: """{app}\{#MyAppExeName}"""; Flags: uninsdeletevalue

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: runascurrentuser shellexec postinstall skipifsilent

[Code]
procedure RunOtherInstaller(FileName: String);
var
  ResultCode: Integer;
begin
  if not Exec(ExpandConstant('{app}\advanced\' + FileName), '', '', SW_SHOWNORMAL,
    ewWaitUntilTerminated, ResultCode)
  then
    MsgBox('Other installer failed to run!' + #13#10 +
      SysErrorMessage(ResultCode), mbError, MB_OK);
end;

