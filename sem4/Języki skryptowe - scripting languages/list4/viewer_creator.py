import mimetypes
from image_viewer import ImageViewer
from text_viewer import TextViewer


class ViewerCreator:

    def __init__(self):
        pass

    def create_viewer(self, path: str):
        return self._detect_viewer_type(path)(path)

    @staticmethod
    def _detect_viewer_type(path: str):
        match mimetypes.guess_type(path)[0].split("/")[0]:  # guess_type zwraca krotke ("type/subtype", encoding)
            case "text":
                return TextViewer
            case "image":
                return ImageViewer
            case _:
                raise ValueError("Invalid file type")
