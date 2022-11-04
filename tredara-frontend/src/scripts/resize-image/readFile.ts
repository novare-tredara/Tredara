// Pure
export default async function readFile(file: File | Blob): Promise<string> {
  const reader = new FileReader();

  reader.readAsDataURL(file);

  const result: string = await new Promise((resolve) => {
    reader.onload = (event) => {
      if (!event.target?.result) return;

      const target = event.target.result as string;

      return resolve(target);
    };
  });

  return result;
}
