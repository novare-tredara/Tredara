// Pure
export default async function resizeImage(
  imageSource: string,
  width: number,
  height: number
) {
  const canvas = document.createElement("canvas");
  const context = canvas.getContext("2d");
  const image = await loadImage(imageSource);
  const newSize = proportionalScaling(image, width, height);

  canvas.width = newSize.width;
  canvas.height = newSize.height;
  context?.drawImage(image, 0, 0, newSize.width, newSize.height);

  return await canvasToPNGBlob(canvas);
}

// Pure
async function loadImage(source: string) {
  const result = new Image();
  result.src = source;

  await new Promise((resolve) => (result.onload = resolve));

  return result;
}

// Pure
function proportionalScaling(
  image: HTMLImageElement,
  width: number,
  height: number
) {
  let newWidth = image.width;
  let newHeight = image.height;

  if (newWidth > newHeight) {
    newHeight *= width / newWidth;
    newWidth = width;
  } else {
    newWidth *= height / newHeight;
    newHeight = height;
  }

  return { width: newWidth, height: newHeight };
}

// Pure
async function canvasToPNGBlob(canvas: HTMLCanvasElement) {
  const canvasToDataURL = canvas.toDataURL("image/png");
  const DataURLToBlob = await fetch(canvasToDataURL);
  const result = await DataURLToBlob.blob();

  return result;
}
