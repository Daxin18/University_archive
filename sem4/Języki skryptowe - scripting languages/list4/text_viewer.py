from re import findall
import subprocess
from file_viewer import FileViewer
from text_buffer import TextBuffer


class TextStats:
    def __init__(self, text: str):
        self.number_of_lines = 0
        self.number_of_words = 0
        self.number_of_nonalpha = 0
        self.compute(text)

    def compute(self, text: str):
        self.number_of_lines = len(text.splitlines())
        self.number_of_words = len(text.split())
        self.number_of_nonalpha = len(findall(r'[^\w]', text))

    def __str__(self):
        return (f"============================\n Lines: {self.number_of_lines}\n"
                f" Words: {self.number_of_words}\n Non-alpha characters: {self.number_of_nonalpha}"
                f"\n============================")


class TextViewer(FileViewer, TextBuffer):
    def __init__(self, path: str):
        FileViewer.__init__(self, path)
        TextBuffer.__init__(self)
        self.read_from_file(path)
        self._stats = TextStats(self.text)

    def view(self):
        try:
            subprocess.run(["notepad.exe", self.path], check=True)
        except subprocess.CalledProcessError as error:
            print("Subprocess exited with a non-zero exit code")
            print(error)
        except:
            print("I have no idea what went wrong")

    def get_data(self):
        return self._stats
