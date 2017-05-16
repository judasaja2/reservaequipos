/////////////////////////////////////////////////////////////////////////////////
// RSA Encryption Algorithm
// Departamento de Recursos de Apoyo e Informatica DRAI
// este algoritmo es para la encryptacion de la contraseña del usuario
// usamos la libreria BitInt de jQuery
//

/** numero de particiones que tendra el codigo cifrado */
var encoding_lenght = 3;
/** Clave pública para sera utilizado en el algoritmo. Se almacena en un tipo de variable <code>BigInteger</code> */
var public_key = str2bigInt("105337396879944889886675", "10", 0);
/** La clave privada para ser utilizado en el algoritmo. Se almacena en un tipo de variable <code>BigInteger<code> */
var private_key = str2bigInt("677380655472689127671011", "10", 0);

/**
 * Encodes a <code>String</code> in 256 base depending of the position into the
 * fragment. Although not taken into account, the lenght of the <code>String</code>
 * corresponding to the variable <code>encoding_lenght</code>. The maximum
 * size of the fragment is limited by the size of the integers
 *
 *
 */
function encode(fragment) {

    /* <code>int</code> representing the fragment encoded */
    var encodedFragment = 0;

    /* Encoding...*/
    for (var i = 0; i < fragment.length; i++)
        encodedFragment = (encodedFragment + fragment.charCodeAt(i) * Math.pow(256, i));

    return encodedFragment;
}

/**
 * It organizes a <code>String</code> in fragments;
 * the size of each fragment is established by variable <code>encoding_lenght</code>.
 *
 * The fragment is completed with blank spaces at the end, to prevent that some
 * fragment have a smaller size defined in variable <code>encoding_lenght</code>.
 *
 * @param message <code>String</code> to fragment
 *
 * @return Array of String with the fragments of the message
 */
function fragments(message) {

    /* Specifies how many blank spaces there are that to add at the end fo the message. */
    var blankSpaces = message.length % encoding_lenght;

    /* Adds the blank spaces at the end of the message.*/
    if (blankSpaces != 0) {
        for (var i = encoding_lenght; i > blankSpaces; i--)
            message = message + " ";
    }

    /* Stores the number of messages resulting of the fragmenting. */
    var numberOfMessages = message.length / encoding_lenght;

    /* Stores each of the fragments of the message. */
    var fragmentsMessage = new Array()

    for (i = 0; i < numberOfMessages; i++)
        fragmentsMessage[i] = message.substring(encoding_lenght * i, encoding_lenght * (i + 1));

    return fragmentsMessage;
}

/**
 * Encrypts a <code>String</code> using the key public, the <code>String</code>
 * is encoded in fragments depending of the size of the encoding established and
 * using the <code>encode</code> function.
 *
 * @param message <code>String</code> that contains the message to encrypt.
 *
 * @return <code>String</code> with the message encoded.
 */
function encrypt2() {

    var message = $("#clave").attr("value");
    var valor2 = $("#usuario").attr("value");

    /* Fragments the original message */
    var messages = fragments(message);

    /* Stores the encoding of the fragments obtained as type BigInteger. */
    var bigEncode = new Array();

    /* Encoding of the fragments obtained. */
    for (var i = 0; i < messages.length; i++)
        bigEncode[i] = str2bigInt("" + encode(messages[i]), "10", 0);

    /* Stores the encrypting of the fragments encoded. */
    var encrypted = new Array();

    /* Encrypts the BigInteger resulting of the encoding. */
    for (i = 0; i < bigEncode.length; i++)
        encrypted[i] = powMod(bigEncode[i], public_key, private_key);

    /* Stored the password encoded*/
    var passwordEncoded = "";

    /* Converts BigInteger to String. */
    for (i = 0; i < encrypted.length; i++)
        encrypted[i] = bigInt2str(encrypted[i], "10");

    for (i = 0; i < encrypted.length; i++) {
        passwordEncoded = passwordEncoded + encrypted[i];
        if ((i + 1) != encrypted.length) {
            passwordEncoded = passwordEncoded + " ";
        }
    }
    //las siguientes dos lineas de codigo envian los datos y ejecutan el servlet
    var codigo = valor2+"+-*"+passwordEncoded;

    $.post("Login",{
        codigo: codigo
    });
}

function encrypt(message) {

    /* Fragments the original message */
    var messages = fragments(message);

    /* Stores the encoding of the fragments obtained as type BigInteger. */
    var bigEncode = new Array();

    /* Encoding of the fragments obtained. */
    for (var i = 0; i < messages.length; i++)
        bigEncode[i] = str2bigInt("" + encode(messages[i]), "10", 0);

    /* Stores the encrypting of the fragments encoded. */
    var encrypted = new Array();

    /* Encrypts the BigInteger resulting of the encoding. */
    for (i = 0; i < bigEncode.length; i++)
        encrypted[i] = powMod(bigEncode[i], public_key, private_key);

    /* Stored the password encoded*/
    var passwordEncoded = "";

    /* Converts BigInteger to String. */
    for (i = 0; i < encrypted.length; i++)
        encrypted[i] = bigInt2str(encrypted[i], "10");

    for (i = 0; i < encrypted.length; i++) {
        passwordEncoded = passwordEncoded + encrypted[i];
        if ((i + 1) != encrypted.length) {
            passwordEncoded = passwordEncoded + " ";
        }
    }

    return passwordEncoded
}
