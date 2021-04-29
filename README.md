# Encoder-Decoder
Java command line application which encodes and decodes messages from a text file using a repeating key

User can run the jar file located in out/artifacts/cryptoKeys_jar/ from the command line or powershell window

First run:
User must first specify encode (e)
Key can be any length but must consist only of numbers
Message can be any length and will be encoded using the key and saved in the provided secrets.txt file

Future runs:
There must be text in the secrets.txt file in order for the decode funtion to work,
If user selects decode (d) they must then provide the correct key for the message to be decoded correctly
