#include <iostream>
#include <Windows.h>
#include "tlib.hpp"

BOOL CALLBACK EnumWindowsProc(HWND hWnd, LPARAM lParam) {
	DWORD dwThreadId, dwProcessId;
	HINSTANCE hInstance;
	char String[255];
	if (!hWnd)
		return TRUE;
	if (!::IsWindowVisible(hWnd))
		return TRUE;
	if (!SendMessage(hWnd, WM_GETTEXT, sizeof(String), (LPARAM)String))
		return TRUE;
	hInstance = (HINSTANCE)GetWindowLong(hWnd, 0);
	dwThreadId = GetWindowThreadProcessId(hWnd, &dwProcessId);
	std::cout << "PID: " << dwProcessId << '\t' << String << '\t' << std::endl;
	return TRUE;
}

int main() {
	const char* dllPath = "C:\\Users\\Marcu\\Desktop\\Matias\\Scripts\\DLL\\x64\\Debug\\Dll2.dll";
	DWORD procId = 0;
	std::cout << "Target Programs:\n" << std::endl;
	EnumWindows(EnumWindowsProc, NULL);
	std::cout << "\n>> ";
	std::cin >> procId;
	

	HANDLE hProc = OpenProcess(PROCESS_ALL_ACCESS, 0, procId);

	if (hProc && hProc != INVALID_HANDLE_VALUE) {
		void* loc = VirtualAllocEx(hProc, 0, MAX_PATH, MEM_COMMIT | MEM_RESERVE, PAGE_READWRITE);

		if (loc) {
			WriteProcessMemory(hProc, loc, dllPath, strlen(dllPath) + 1, 0);
		}

		HANDLE hThread = CreateRemoteThread(hProc, 0, 0, (LPTHREAD_START_ROUTINE)LoadLibraryA, loc, 0, 0);

		if (hThread) {
			CloseHandle(hThread);
		}
	}

	if (hProc) {
		CloseHandle(hProc);
	}

	using namespace tlib;
	ExitProgram(Exit_Success);
}