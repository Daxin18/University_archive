import subprocess
from file_viewer import FileViewer


class ImageViewer(FileViewer):

    def __init__(self, path: str):
        super().__init__(path)

    def view(self):
        try:
            subprocess.run(["mspaint.exe", self.path], check=True)
        except subprocess.CalledProcessError as error:
            print("Subprocess exited with non-zero exit code")
            print(error)
        except:
            print("I have no idea what went wrong")
