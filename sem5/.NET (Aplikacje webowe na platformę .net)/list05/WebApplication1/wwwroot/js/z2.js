

function clearCanvas(ctx)
{
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
}

function drawLine(ctx, mouseX, mouseY, cornerX, cornerY)
{
    ctx.beginPath(); //bez tej linijki kodu, linie nie beda sie czyscic
    ctx.moveTo(mouseX, mouseY);
    ctx.lineTo(cornerX, cornerY);
    ctx.strokeStyle = "blue";
    ctx.stroke();
}

function fixCanvas(canvas) //funkcja ustawiajaca logiczna szerokosc i wysokosc kanwy na ich faktyczne wartosci
{
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;
}

const canvases = document.getElementsByTagName("canvas");

for (const canvas of canvases)
{
    const ctx = canvas.getContext("2d");

    canvas.addEventListener("mousemove", (event) => {
        const {offsetX: mX, offsetY: mY} = event; //offset sprawia, ze pozycja myszki jest zwracana wzgledem elementu

        fixCanvas(canvas);
        clearCanvas(ctx);
        drawLine(ctx, mX, ctx.canvas.height, mX, 0);
        drawLine(ctx, ctx.canvas.width, mY, 0, mY);
        ctx.beginPath();
        ctx.arc(mX, mY, 20, 0, 2*Math.PI);
        ctx.stroke();
    });

    canvas.addEventListener("mouseleave", () => clearCanvas(ctx));
};
