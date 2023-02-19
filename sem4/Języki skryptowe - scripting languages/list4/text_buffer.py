
class TextBuffer:
    def __init__(self, text=''):
        self.text = text

    def read_from_file(self, path: str):
        file = open(path, 'r')
        self.text = file.read()
