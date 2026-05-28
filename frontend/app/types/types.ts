export interface Register {

    email: string;
    password: string;
    password_confirmation: string;

}

export interface Login {

    identifier: string;
    password: string;

}

export interface AuthReturn { success: boolean, data: { username: string, token: string, userId: number, email: string, lesson: number } }

export interface LessonsDetails {

    [id: string]: {

        name: string;
        goals: string[];

    }

}

/****************Alignment*****************/
export interface AlignmentResult {

    status: string;
    transcription: string;
    reference_text: string;
    alignment: Alignment[]
    audio_duration: number;
    confidence_stats: ConfidenceStats;

}

export interface Alignment {

    token: string;
    start: number;
    end: number;
    confidence: number;

}

export interface ConfidenceStats {

    mean: number;
    min: number;
    max: number;

}
/****************Alignment*****************/

export interface Roleplay {

    [id: string]: RoleplayQuestion

}

export interface RoleplayQuestion {

    answer: string;
    question: string;
    ignore: [[number, number, string]]

}

export interface Flashcard {

    pairs: [ [ string, string ] ]

}

export interface FlashcardPair {

    cantonese: string;
    translation: string;

}

export interface Grammar {

    [id: string]: GrammarItem

}

export interface GrammarItem {

    grammar: string;
    explanation: string;
    examples: [ [ string, string ] ];
    exercises: [ ...string[] ]

}

export interface Communication {

    [id: string]: CommunicationQuestion;

}

export interface CommunicationQuestion {

    question: string;
    description: string;
    answer: string;

}

export interface Conversation {

    a: Role;
    b: Role;

}

export interface Role {

    name: string;
    intro: string;
    lines: Line[]

}

export interface Line {

    id: string;
    timestamp: string;
    content: string;

}

/* STATES */
export interface UserInfo { username: string, userId: number, email: string, nationality: string, lesson: number, bio: string }

export interface Comment {

    id: number;
    title: string;
    comment: string;
    rating: number;
    user: UserInfo;

}

export type ProfileFormExpose = {

    getFormValues: () => UserInfo;
    resetForm: () => void

}

export interface HTMLInputEvent extends Event {

    target: HTMLInputElement & EventTarget;

}