export function getRandomInt(max: number) {

    return Math.floor( Math.random() * max ) + 1;

}

export function shuffleArrayOrder<T>( length: number ): T[] {

    let order: T[] = [];

    for( let i = 0; i < length; i++ ) {

        let question: T;

        do {

            question = getRandomInt(length) as T;

        } while( order.includes( question ) );

        order.push( question );

    }

    return order;

}

// MAINLY FOR GRAMMAR
export function replaceWithSymbol( rawString: string, regex: RegExp, symbol: string ): string {

    return rawString.replace( regex, symbol );

}

export function getWordWithTrans( rawString: string, splitWith: string ): string {

    if( !rawString.includes( splitWith ) ) return `${rawString}(Please contact us)`;

    const splited: string[] = rawString.split( splitWith );

    return `${splited[0] ?? rawString}(${splited[1]?? "Translation Not Provided"})`;

}

export function splitTranslation( rawString: string, splitWith: string ): [ cantonese: string, translation: string ] {

    if( !rawString.includes( splitWith ) ) return [ rawString, "Translation Not Provided" ];

    const splited: string[] = rawString.split( splitWith );

    return [ splited[0] ?? rawString, splited[1]?? "Translation Not Provided" ];

}

export function toSeconds( timeString: string ) {

    const minutes = timeString.split(":")[0] ?? '0';
    const seconds = timeString.split(":")[1] ?? '0';

    return parseInt( minutes ) * 60 + parseInt( seconds );

}