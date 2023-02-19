from abc import ABC, abstractmethod


class FileViewer(ABC):

    def __init__(self, path: str):
        self.path = path

    @abstractmethod
    def view(self):
        pass
